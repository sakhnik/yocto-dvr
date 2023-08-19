#!/bin/sh

# Check and create a partition for videos

device=/dev/mmcblk0

# Partition table
part=$(fdisk -l $device)
if ! (echo "$part" | grep -q "${device}p3"); then
    # Total number of available sectors
    sectors=$(echo "$part" | sed -n '/^Disk/ s/.* \([0-9]\+\) sectors/\1/p')
    # Current end of the fs partition
    end=$(echo "$part" | grep "${device}p2" | awk '{print $5}')
    start=$((end + 1))
    logger "Creating a partition for videos"
    echo -e "n\np\n3\n${start}\n\nt\n3\nc\nw" | fdisk $device
    # The change won't have effect until next reboot because the device is mounted
    reboot
fi

# Format the video partition if required
if [ "$(blkid ${device}p3)" == "" ]; then
    mkfs.vfat -n VIDEO ${device}p3
fi
