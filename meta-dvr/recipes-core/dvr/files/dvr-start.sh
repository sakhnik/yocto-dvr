#!/bin/sh

dvr-resizefs.sh

connmanctl enable wifi

while true; do
  ip addr
  sleep 10
end
