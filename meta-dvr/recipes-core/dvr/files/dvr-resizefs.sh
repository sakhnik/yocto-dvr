#!/bin/sh

# Expand fs partition to the maximum size

device=/dev/mmcblk0

# Partition table
part=$(fdisk -l $device)
# Total number of available sectors
sectors=$(echo "$part" | sed -n '/^Disk/ s/.* \([0-9]\+\) sectors/\1/p')
# Current start of the last partition
start=$(echo "$part" | tail -1 | awk '{print $4}')
# Current end of the last partition
end=$(echo "$part" | tail -1 | awk '{print $5}')
if [ $((end + 1)) -lt $sectors ]; then
    logger "Resizing the fs partition"
    echo -e "d\n2\nn\np\n2\n${start}\n\nw" | fdisk $device
    # The change won't have effect until next reboot because the device is mounted
    reboot
fi

resize2fs ${device}p2
