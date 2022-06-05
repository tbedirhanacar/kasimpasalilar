package com.tbedirhanacar.kasimpasalilar;

import org.javacord.api.DiscordApi;

import java.util.concurrent.TimeUnit;

public class UpdateActivityConstantly implements Runnable{

    DiscordApi api;

    public UpdateActivityConstantly(DiscordApi api) {
        this.api = api;
    }

    @Override
    public void run() {
        while (true){
            try {
                api.updateActivity("KASIMPAŞAYA HOŞGELDİNİZ");
                TimeUnit.MILLISECONDS.sleep(5000);
                api.updateActivity("DESTEK İÇİN /destek");
                TimeUnit.MILLISECONDS.sleep(2500);
            }catch (Exception e){
            }
        }
    }
}
