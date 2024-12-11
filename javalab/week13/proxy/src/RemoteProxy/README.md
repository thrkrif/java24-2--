
1. Change directories to the same level with bin/

2. Run rmiregistry in background:
rmiregistry &

3. Run:
java RemoteProxy/MyRemoteImpl

4. In a different window, run:
java RemoteProxy/MyRemoteClient


