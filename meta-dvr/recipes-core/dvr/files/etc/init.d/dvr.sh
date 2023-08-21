#!/bin/sh

PATH=/sbin:/bin:/usr/sbin:/usr/bin
DAEMON=/usr/bin/dvr.sh
PIDFILE=/var/run/dvr.pid
NAME=dvr.sh
DESC="DVR"

case "$1" in
  start)
	echo -n "Starting $DESC: "
	start-stop-daemon --start --startas "$DAEMON" --pidfile "$PIDFILE" --background --make-pidfile
	echo "$NAME."
	;;
  stop)
	echo -n "Stopping $DESC: "
	start-stop-daemon --stop --name "$NAME" --pidfile "$PIDFILE"
	rm "$PIDFILE"
	echo "$NAME."
	;;
  restart|force-reload)
	echo -n "Restarting $DESC: "
	start-stop-daemon --stop --name "$NAME" --pidfile "$PIDFILE"
	sleep 1
	start-stop-daemon --start -startas "$DAEMON" --pidfile "$PIDFILE" --background --make-pidfile
	echo "$NAME."
	;;
  *)
	N=/etc/init.d/$NAME
	echo "Usage: $N {start|stop|restart|force-reload}" >&2
	exit 1
	;;
esac

exit 0
