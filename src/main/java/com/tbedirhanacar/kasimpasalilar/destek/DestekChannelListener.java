package com.tbedirhanacar.kasimpasalilar.destek;

import com.tbedirhanacar.kasimpasalilar.ModeratorChecker;
import org.javacord.api.event.message.MessageCreateEvent;
import org.javacord.api.listener.message.MessageCreateListener;

public class DestekChannelListener implements MessageCreateListener {
    @Override
    public void onMessageCreate(MessageCreateEvent event) {
        if (event.getChannel().getIdAsString().equals(DestekMessageListener.destekKanalıID)
                && !ModeratorChecker.isModerator(event.getMessageAuthor().asUser().get(),event.getServer().get())){
            event.getMessage().delete();
        }
    }
}
