package com.tbedirhanacar.kasimpasalilar;

import com.tbedirhanacar.kasimpasalilar.destek.DestekMessageListener;
import org.javacord.api.entity.permission.PermissionType;
import org.javacord.api.entity.permission.Role;
import org.javacord.api.entity.server.Server;
import org.javacord.api.entity.user.User;

import java.util.List;

public class ModeratorChecker {

    public static boolean isModerator(User user, Server server){
        List<Role> roles = user.getRoles(server);
        for (Role role: roles) {
            if (role.getIdAsString().equals(DestekMessageListener.destekGorevlisiID)){
                return true;
            }
        }
        return false;
    }

}
