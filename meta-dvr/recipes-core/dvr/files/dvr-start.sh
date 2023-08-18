#!/bin/sh

# First connect to WiFi
connmanctl enable wifi

# Make sure there's partition for video storage
dvr-mkst.sh

while true; do
  ip addr
  sleep 10
end
