include recipes-core/images/core-image-base.bb

DISTRO_FEATURES += " \
  wifi \
"

IMAGE_FEATURES += " \
  ssh-server-dropbear \
  debug-tweaks \
"

IMAGE_EXTRA_FEATURES += " \
  inetutils inetutils-telnetd \
"

IMAGE_INSTALL += " \
  connman \
  connman-client \
  linux-firmware-bcm43430 \
"

customize_image() {
  #rm -f ${IMAGE_ROOTFS}/etc/rcS.d/*keymap.sh
  #rm -f ${IMAGE_ROOTFS}/etc/rcS.d/*bootlogd
  #rm -f ${IMAGE_ROOTFS}/etc/rcS.d/*urandom
  #rm -f ${IMAGE_ROOTFS}/etc/rcS.d/*banner.sh
  #rm -f ${IMAGE_ROOTFS}/etc/rcS.d/*populate-volatile.sh
  #rm -f ${IMAGE_ROOTFS}/etc/rcS.d/*dmesg.sh
  #rm -f ${IMAGE_ROOTFS}/etc/rcS.d/*bootmisc.sh

  #rm -f ${IMAGE_ROOTFS}/etc/rc5.d/*syslog
  #rm -f ${IMAGE_ROOTFS}/etc/rc5.d/*rmnologin.sh
  #rm -f ${IMAGE_ROOTFS}/etc/rc5.d/*mountnfs.sh
  #rm -f ${IMAGE_ROOTFS}/etc/rc5.d/*stop-bootlogd
  #rm -f ${IMAGE_ROOTFS}/etc/rc5.d/*networking

  echo "dvr" > ${IMAGE_ROOTFS}/etc/hostname

  #echo "tz:5:respawn:/usr/bin/start_dvr" >> ${IMAGE_ROOTFS}/etc/inittab
  echo "tz:5:wait:/etc/debug" >> ${IMAGE_ROOTFS}/etc/inittab
  echo -e "#!/bin/sh\nip addr > /debug\n" > ${IMAGE_ROOTFS}/etc/debug
  chmod +x ${IMAGE_ROOTFS}/etc/debug

  echo "LABEL=boot /boot vfat defaults 0 0" >> ${IMAGE_ROOTFS}/etc/fstab
}

ROOTFS_POSTPROCESS_COMMAND:append = " customize_image; "
