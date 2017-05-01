# team-city-agent-auto-auth
Team City server plugin to automatically authorize agents on register

For organizations that heavily automate their build agent infrastructure, the manual step of authorizing and unauthorizing agents can become an issue. For example, agents setup in an AWS Auto Scaling Group could appear and disappear multiple times a day.

"Agent Auto Authorize" listens for agent registrations and automatically authorizes them. Additionally, if the `auto_deregister` flag is set, the agent will be de-authorised on disconnection.

NOTE: Because this plugin circumvents the manual agent authorization step which is guarded by TeamCity authentication, only use this if your TeamCity server is inaccessible to the public Internet.

# Building the Plugin

1. Coming Soon

# Installation

1. Upload `AutoAuthorize.zip` to your TeamCity plugin directory. (If you're doing this through the UI, you can find it at https://your-teamcity/admin/admin.html?item=plugins)
2. Restart your TeamCity server
3. Add the following to your agent properties (located at `$agentDir/conf/buildAgent.properties`): `auto_authorize=true`
4. Start your build agent
5. Note that as soon as it's registered, it will also be authorized.