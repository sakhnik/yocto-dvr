include recipes-core/images/core-image-base.bb

DISTRO_FEATURES += " \
  wifi \
"

IMAGE_FEATURES += " \
  ssh-server-dropbear \
  debug-tweaks \
"

IMAGE_INSTALL += " \
  connman \
  connman-client \
  dvr \
  linux-firmware-bcm43430 \
"

customize_image() {
  rm -f ${IMAGE_ROOTFS}/etc/rcS.d/*keymap.sh
  rm -f ${IMAGE_ROOTFS}/etc/rcS.d/*bootlogd
  rm -f ${IMAGE_ROOTFS}/etc/rcS.d/*urandom
  rm -f ${IMAGE_ROOTFS}/etc/rcS.d/*banner.sh
  rm -f ${IMAGE_ROOTFS}/etc/rcS.d/*populate-volatile.sh
  rm -f ${IMAGE_ROOTFS}/etc/rcS.d/*dmesg.sh
  rm -f ${IMAGE_ROOTFS}/etc/rcS.d/*bootmisc.sh

  rm -f ${IMAGE_ROOTFS}/etc/rc5.d/*syslog
  rm -f ${IMAGE_ROOTFS}/etc/rc5.d/*rmnologin.sh
  rm -f ${IMAGE_ROOTFS}/etc/rc5.d/*mountnfs.sh
  rm -f ${IMAGE_ROOTFS}/etc/rc5.d/*stop-bootlogd
  rm -f ${IMAGE_ROOTFS}/etc/rc5.d/*networking

  echo "dvr" > ${IMAGE_ROOTFS}/etc/hostname
  echo "LABEL=boot /boot vfat defaults 0 0" >> ${IMAGE_ROOTFS}/etc/fstab

  # Connnect to predetermined wifi automatically
  mkdir -p ${IMAGE_ROOTFS}/var/lib/connman
  cat >${IMAGE_ROOTFS}/var/lib/connman/beefarm.config <<END
[service_beefarm]
Type = wifi
Name = Beefarm2G
Passphrase = df8bc97315b948666f1abfb61dfc0da50e93e273acfc3a0a8e5bbbe83baf61bc
END

  echo "tz:5:respawn:/etc/init.d/dvr-start.sh" >> ${IMAGE_ROOTFS}/etc/inittab
}

ROOTFS_POSTPROCESS_COMMAND:append = " customize_image; "
