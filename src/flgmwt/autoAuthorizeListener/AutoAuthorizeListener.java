/*
 * Copyright 2000-2016 JetBrains s.r.o.
 *
 * Licensed under the Apache License, Version 2.0 (the "License");
 * you may not use this file except in compliance with the License.
 * You may obtain a copy of the License at
 *
 * http://www.apache.org/licenses/LICENSE-2.0
 *
 * Unless required by applicable law or agreed to in writing, software
 * distributed under the License is distributed on an "AS IS" BASIS,
 * WITHOUT WARRANTIES OR CONDITIONS OF ANY KIND, either express or implied.
 * See the License for the specific language governing permissions and
 * limitations under the License.
 */

package flgmwt.autoAuthorizeListener;

import java.util.*;

import jetbrains.buildServer.serverSide.BuildServerAdapter;
import jetbrains.buildServer.serverSide.SBuildAgent;
import jetbrains.buildServer.serverSide.SBuildServer;
import org.jetbrains.annotations.NotNull;


/**
 * Authorizes agents as soon as they're registered
 *
 * CHANGELOG
 * Updated 1/05/2017 by Neko-Design
 *   - Removed Requirement to Flag Agents
 *   - Changed Flag to Auto Disconnect Agents
 */
public class AutoAuthorizeListener extends BuildServerAdapter {
    private final SBuildServer myBuildServer;

    public AutoAuthorizeListener(SBuildServer sBuildServer) {
        myBuildServer = sBuildServer;
    }

    public void register() {
        myBuildServer.addListener(this);
    }

    @Override
    public void agentRegistered(@NotNull SBuildAgent sBuildAgent, long l) {
        sBuildAgent.setAuthorized(true, null, "Dynamic Agent Connected");
    }

    @Override
    public void agentUnregistered(@NotNull SBuildAgent sBuildAgent) {
        Map<String,String> parameters = sBuildAgent.getAvailableParameters();
        if (parameters.containsKey("auto_deregister") && parameters.get("auto_deregister").equals("true")) {
            sBuildAgent.setAuthorized(false, null, "Dynamic Agent Disconnected");
        }
    }
}
