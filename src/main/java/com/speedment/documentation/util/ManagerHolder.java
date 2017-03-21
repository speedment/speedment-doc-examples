package com.speedment.documentation.util;

import com.speedment.datamodel.HaresApplication;
import com.speedment.datamodel.db0.hares.carrot.CarrotManager;
import com.speedment.datamodel.db0.hares.friend.FriendManager;
import com.speedment.datamodel.db0.hares.hare.HareManager;
import com.speedment.datamodel.db0.hares.human.HumanManager;

/**
 *
 * @author Per Minborg
 */
public class ManagerHolder {

    protected final HareManager hares;
    protected final CarrotManager carrots;
    protected final HumanManager humans;
    protected final FriendManager friends;

    public ManagerHolder(final HaresApplication app) {
        this.hares = app.getOrThrow(HareManager.class);
        this.carrots = app.getOrThrow(CarrotManager.class);
        this.humans = app.getOrThrow(HumanManager.class);
        this.friends = app.getOrThrow(FriendManager.class);
    }

}
