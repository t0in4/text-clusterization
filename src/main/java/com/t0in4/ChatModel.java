package com.t0in4;

import chat.giga.client.auth.AuthClient;
import chat.giga.client.auth.AuthClientBuilder;
import chat.giga.langchain4j.GigaChatChatModel;
import chat.giga.langchain4j.GigaChatChatRequestParameters;
import chat.giga.model.ModelName;
import chat.giga.model.Scope;
import io.github.cdimascio.dotenv.Dotenv;

import java.util.logging.Logger;

public class ChatModel {
    private static final Logger LOG = Logger.getLogger(ChatModel.class.getName());
    private static dev.langchain4j.model.chat.ChatModel instance;

    public dev.langchain4j.model.chat.ChatModel getInstance() {
        if (instance == null) {
            synchronized (ChatModel.class) {
                if (instance == null) {
                    Dotenv dotenv = Dotenv.load();
                    String authKey = dotenv.get("GIGACHAT_AUTH_KEY");
                    if (authKey == null || authKey.isBlank()) {
                        throw new IllegalStateException("GIGACHAT_AUTH_KEY is required in .env");
                    }
                    AuthClient authClient = AuthClient.builder()
                            .withOAuth(AuthClientBuilder.OAuthBuilder.builder()
                                    .scope(Scope.GIGACHAT_API_PERS)
                                    .authKey(authKey)
                                    .build())
                            .build();

                    instance = GigaChatChatModel.builder()
                            .authClient(authClient)
                            .defaultChatRequestParameters(GigaChatChatRequestParameters.builder()
                                    .modelName(ModelName.GIGA_CHAT_2)
                                    .temperature(0.0)
                                    .build())
                            .build();
                }
            }
        }
        return instance;
    }
}
