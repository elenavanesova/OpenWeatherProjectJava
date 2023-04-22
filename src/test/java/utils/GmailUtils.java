package utils;

import com.google.api.client.auth.oauth2.Credential;
import com.google.api.client.extensions.java6.auth.oauth2.AuthorizationCodeInstalledApp;
import com.google.api.client.extensions.jetty.auth.oauth2.LocalServerReceiver;
import com.google.api.client.googleapis.auth.oauth2.GoogleAuthorizationCodeFlow;
import com.google.api.client.googleapis.auth.oauth2.GoogleClientSecrets;
import com.google.api.client.googleapis.javanet.GoogleNetHttpTransport;
import com.google.api.client.http.javanet.NetHttpTransport;
import com.google.api.client.json.gson.GsonFactory;
import com.google.api.client.util.Base64;
import com.google.api.client.util.store.FileDataStoreFactory;
import com.google.api.services.gmail.Gmail;
import com.google.api.services.gmail.GmailScopes;
import com.google.api.services.gmail.model.ListMessagesResponse;
import com.google.api.services.gmail.model.Message;

import java.io.*;
import java.nio.file.Paths;
import java.util.*;
import java.io.IOException;
import java.util.List;

import org.jsoup.Jsoup;
import org.jsoup.nodes.Document;
import org.jsoup.nodes.Element;

public class GmailUtils {
    private static final String CREDENTIALS_FILE_PATH = "/credentials.json";
    private static List<String> scopes = Arrays.asList(GmailScopes.MAIL_GOOGLE_COM);
    private static String userID = "me";
    private static String query = "in:inbox";

    public static Gmail getGmailService() throws Exception {
        NetHttpTransport httpTransport = GoogleNetHttpTransport.newTrustedTransport();
        GsonFactory jsonFactory = GsonFactory.getDefaultInstance();
        Gmail service = new Gmail.Builder(httpTransport, jsonFactory, getCredentials(httpTransport, jsonFactory))
                .setApplicationName("Test Mailer")
                .build();

        return service;
    }

    private static Credential getCredentials(final NetHttpTransport httpTransport, GsonFactory jsonFactory) throws IOException {
        InputStream in = GmailUtils.class.getResourceAsStream(CREDENTIALS_FILE_PATH);
        if (in == null) {
            throw new FileNotFoundException("Resource not found: " + CREDENTIALS_FILE_PATH);
        }
        GoogleClientSecrets clientSecrets = GoogleClientSecrets.load(jsonFactory, new InputStreamReader(in));

        GoogleAuthorizationCodeFlow flow = new GoogleAuthorizationCodeFlow.Builder(
                httpTransport, jsonFactory, clientSecrets, scopes)
                .setDataStoreFactory(new FileDataStoreFactory(Paths.get("tokens").toFile()))
                .setAccessType("offline")
                .build();

        LocalServerReceiver receiver = new LocalServerReceiver.Builder().setPort(8888).build();
        Credential credential = new AuthorizationCodeInstalledApp(flow, receiver).authorize("user");

        return credential;
    }

    public static void deleteAllMessages(Gmail service) throws Exception {
        ListMessagesResponse response = service.users().messages().list(userID).execute();
        List<Message> messages = response.getMessages();
        for (Message message : messages) {
            service.users().messages().delete(userID, message.getId()).execute();
        }
    }

    public static boolean isMailExist(String messageTitle) {
        try {
            Gmail service = getGmailService();
            ListMessagesResponse response = service.
                    users().
                    messages().
                    list(userID).
                    setQ("subject:" + messageTitle).
                    execute();
            List<Message> messages = getMessages(response);
//            System.out.println(messages != null && !messages.isEmpty());;
            return messages != null && !messages.isEmpty();
        } catch (Exception e) {
            System.out.println("Exception log" + e);
            return false;
        }
    }

    public static List<Message> getMessages(ListMessagesResponse response) {
        List<Message> messages = new ArrayList<Message>();
        try {
            while (response.getMessages() != null) {
                messages.addAll(response.getMessages());
                if (response.getNextPageToken() != null) {
                    String pageToken = response.getNextPageToken();
                    response = getGmailService().users().messages().list(userID)
                            .setPageToken(pageToken).execute();
                } else {
                    break;
                }
            }
            return messages;
        } catch (Exception e) {
            System.out.println("Exception log " + e);
            return messages;
        }
    }

    public static String getMailBody(Gmail service, String searchString) throws IOException {
        Gmail.Users.Messages.List request = service.users().messages().list(userID).setQ(searchString);
        ListMessagesResponse messageResponse = request.execute();
        request.setPageToken(messageResponse.getNextPageToken());
        String messageId = messageResponse.getMessages().get(0).getId();
        Message message = service.users().messages().get(userID, messageId).execute();
        String emailBody = new String(Base64.decodeBase64(message.getPayload().getBody().getData()));
//        System.out.println(emailBody);

        return emailBody;
    }

    public static String getHrefLink(Gmail service, String searchString) throws IOException {
        String emailBody = getMailBody(service, searchString);
        Document doc = Jsoup.parse(emailBody);
        Element link = doc.select("a[href]").first();
        String url = link.attr("href");
        System.out.println(url);

        return url;
    }

    public static long getTotalCountOfMails(Gmail service) throws IOException {
        ListMessagesResponse response = service.users().messages().list(userID).setQ(query).execute();
        long totalMessages = response.getResultSizeEstimate();
        System.out.println(totalMessages);

        return totalMessages;
    }
}
