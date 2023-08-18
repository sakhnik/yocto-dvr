#!/bin/sh

# First connect to WiFi
connmanctl enable wifi

# Make sure there's partition for video storage
dvr-mkst.sh

# Run the video capture
dvr.sh
