package com.tbedirhanacar.kasimpasalilar.destek;

import org.javacord.api.DiscordApi;
import org.javacord.api.entity.channel.ServerThreadChannel;
import org.javacord.api.entity.message.Message;
import org.javacord.api.event.interaction.SlashCommandCreateEvent;
import org.javacord.api.interaction.SlashCommandInteraction;
import org.javacord.api.interaction.callback.InteractionCallbackDataFlag;
import org.javacord.api.listener.interaction.SlashCommandCreateListener;

import java.util.concurrent.TimeUnit;

public class DestekMessageListener implements SlashCommandCreateListener {

    public static String destekGorevlisiID = "983033213712752730";//983035073337098290
    static String destekKanalıID = "983043545533395045";//982726206606704700
    private static DiscordApi api;

    public DestekMessageListener(DiscordApi api) {
        this.api = api;
    }

    @Override
    public void onSlashCommandCreate(SlashCommandCreateEvent event) {
        SlashCommandInteraction slashCommandInteraction = event.getSlashCommandInteraction();
        if (slashCommandInteraction.getCommandName().equals("destek")) {
            if (slashCommandInteraction.getChannel().get().getIdAsString().equals(destekKanalıID)){
                try {
                    String userName = slashCommandInteraction.getUser().getDisplayName(slashCommandInteraction.getServer().get());
                    Message msg = api.getServerTextChannelById(destekKanalıID).get().sendMessage("Destek kanalını oluşturuyoruz, lütfen bekle!").get();

                    ServerThreadChannel stc = msg.createThread(userName+" için destek kanalı", 1440).get();
                    Message destekGorevlisiMentionMessage = stc.sendMessage("<@&"+destekGorevlisiID+">").get();

                    Message msgInThread = stc.sendMessage(slashCommandInteraction.getUser().getMentionTag()+" Lütfen bekle moderatörler sana yardım edecek!\n*Bu kanal sadece "+userName+" tarafından kullanılmalıdır!*\n" +
                            "**Destek işlemini sonlandırmak için aşağıdaki çarpıya basın**").get();
                    msgInThread.addReaction("❌");

                    destekGorevlisiMentionMessage.delete();
                    msgInThread.addReactionAddListener(new CloseReactionListener(stc, slashCommandInteraction.getUser())).removeAfter(1440, TimeUnit.MINUTES);
                    msg.delete();
                    slashCommandInteraction.createImmediateResponder()
                            .setContent("Destek kanalını oluşturduk, işte burada! " + stc.getMentionTag())
                            .setFlags(InteractionCallbackDataFlag.EPHEMERAL)
                            .respond();
                } catch (Exception e) {
                    e.printStackTrace();
                }
            }else {
                slashCommandInteraction.createImmediateResponder()
                        .setContent("Bu kanalda destek işlemi uygulanmıyor. Lütfen <#"+destekKanalıID+"> kanalını kullan!")
                        .setFlags(InteractionCallbackDataFlag.EPHEMERAL)
                        .respond();
            }
        }
    }
}
