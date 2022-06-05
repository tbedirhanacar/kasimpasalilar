package com.tbedirhanacar.kasimpasalilar.destek;

import com.tbedirhanacar.kasimpasalilar.ModeratorChecker;
import org.javacord.api.entity.channel.ServerThreadChannel;
import org.javacord.api.entity.message.Message;
import org.javacord.api.entity.user.User;
import org.javacord.api.event.message.reaction.ReactionAddEvent;
import org.javacord.api.listener.message.reaction.ReactionAddListener;

import java.util.*;
import java.util.concurrent.TimeUnit;

public class CloseReactionListener implements ReactionAddListener {

    private ServerThreadChannel stc;
    private User userRequestedSupport;

    public CloseReactionListener(ServerThreadChannel stc, User userRequestedSupport) {
        this.userRequestedSupport = userRequestedSupport;
        this.stc = stc;
        Runnable deleteStcRunnable = ()->{
            try {
                TimeUnit.MINUTES.sleep(1440);
                stc.delete();
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
        };
        Thread deleteStcThread = new Thread(deleteStcRunnable);
        deleteStcThread.start();
    }

    @Override
    public void onReactionAdd(ReactionAddEvent event) {
            if (event.getEmoji().equalsEmoji("❌")) {
                if (!event.getUser().get().isBot()) {
                    if (ModeratorChecker.isModerator(event.getUser().get(), event.getServer().get())||event.getUser().get().equals(userRequestedSupport)) {
                        event.getMessage().get().delete();
                        stc.delete();
                    } else {
                        event.getReaction().get().removeUser(event.getUser().get());
                    }
                }
            } else {
                event.getReaction().get().remove();
            }

    }
    ;

}
