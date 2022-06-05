package com.tbedirhanacar.kasimpasalilar;

import com.tbedirhanacar.kasimpasalilar.destek.DestekChannelListener;
import com.tbedirhanacar.kasimpasalilar.destek.DestekMessageListener;
import org.javacord.api.DiscordApi;
import org.javacord.api.DiscordApiBuilder;
import org.javacord.api.interaction.SlashCommand;


public class Main {
    public static void main(String[] args) {

        DiscordApi api = new DiscordApiBuilder().setToken(System.getenv("TOKEN")).setAllIntents().login().join();
        Thread activityUpdateThread = new Thread(new UpdateActivityConstantly(api));
        activityUpdateThread.start();

        SlashCommand.with("destek", "Moderatörlerden destek almak için bu komutu kullanın")
                .createGlobal(api).join();
        api.addListener(new DestekMessageListener(api));
        api.addListener(new DestekChannelListener());


        System.out.println("Bot activated");

    }
}
