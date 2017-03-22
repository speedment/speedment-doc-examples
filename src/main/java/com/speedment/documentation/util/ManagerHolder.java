/**
 *
 * Copyright (c) 2006-2017, Speedment, Inc. All Rights Reserved.
 *
 * Licensed under the Apache License, Version 2.0 (the "License"); You may not
 * use this file except in compliance with the License. You may obtain a copy of
 * the License at:
 *
 * http://www.apache.org/licenses/LICENSE-2.0
 *
 * Unless required by applicable law or agreed to in writing, software
 * distributed under the License is distributed on an "AS IS" BASIS, WITHOUT
 * WARRANTIES OR CONDITIONS OF ANY KIND, either express or implied. See the
 * License for the specific language governing permissions and limitations under
 * the License.
 */
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
