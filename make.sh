#!/usr/bin/env sh

case $1 in
	clean)
		rm -rf bin
	;;

	*)
		mkdir -p bin
		find src -name '*.java' | xargs javac -d bin
	;;
esac
