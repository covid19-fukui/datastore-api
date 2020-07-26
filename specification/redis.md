# Redis memo
```sh 
# For mac
$ brew install redis
```

## redis trouble
redisのキャッシュが更新されない場合に以下手順で確認する。
```
# ログイン
$ redis-cli -h <hostname> -p <port> -a <password>
# 疎通確認
> ping
PONG
# キーの値が存在するか確認する
> EXISTS <key>
# キーがexpireされる残り時間(TTL)を確認する
> TTL <key>
(integer) 214
# キーに対応する値を取得する
> get <key>
...
# キーの値を削除する
> del <key>
```