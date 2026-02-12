package com.alibaba.cloud.ai.mcp.client;

import org.springframework.ai.chat.client.ChatClient;
import org.springframework.ai.chat.client.advisor.SimpleLoggerAdvisor;
import org.springframework.ai.tool.ToolCallbackProvider;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.ConfigurableApplicationContext;
import org.springframework.context.annotation.Bean;
import org.springframework.util.StreamUtils;
import org.springframework.util.StringUtils;

import java.util.Scanner;

/**
 * @author yingzi
 * @since 2025/11/2
 */
@SpringBootApplication
public class StreamableClientApplication {

    @Value("${spring.ai.dashscope.api-key}")
    private String apiKey;

    public static void main(String[] args) {
        SpringApplication.run(StreamableClientApplication.class, args);
    }

    @Bean
    public CommandLineRunner predefinedQuestions(
            ChatClient.Builder chatClientBuilder,
            ToolCallbackProvider tools,
            ConfigurableApplicationContext context
    ) {
        System.out.println("---------------------------------");
        // System.out.println("api-key: " + this.apiKey);
        System.out.println("---------------------------------");

        return args -> {
            var chatClient = chatClientBuilder
                    .defaultToolCallbacks(tools.getToolCallbacks())
                    .defaultAdvisors(new SimpleLoggerAdvisor()) // 输出不全
                    .build();

            Scanner scanner = new Scanner(System.in);
            while (true) {
                System.out.print("\n>>> QUESTION: ");
                String userInput = scanner.nextLine();
                if (userInput.equalsIgnoreCase("exit")) {
                    break;
                }
                System.out.println("\n>>> ASSISTANT: " + chatClient.prompt(userInput).call().content());
            }
            scanner.close();
            context.close();
        };
    }

}
