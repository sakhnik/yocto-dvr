SUMMARY = "DVR application"

LICENSE = "MIT"
LIC_FILES_CHKSUM = "file://COPYING;md5=71bec9001bfc79fcaa9f0024c35e8cdd"

DEPENDS = ""
RDEPENDS:${PN} = " \
  connman-client \
  ffmpeg \
  lighttpd \
  userland \
"

SRC_URI = " \
  file://COPYING \
  file://dvr-http.conf \
  file://dvr-init.sh \
  file://dvr-mkst.sh \
  file://dvr-rc.sh \
  file://dvr.conf \
  file://dvr.sh \
"

S = "${WORKDIR}"

do_configure() {
}

do_compile() {
}

do_install() {
    install -d ${D}${sysconfdir}
    install -m 0644 ${WORKDIR}/dvr.conf ${D}${sysconfdir}

    install -d ${D}${bindir}
    install -m 0755 ${WORKDIR}/dvr-mkst.sh ${D}${bindir}
    install -m 0755 ${WORKDIR}/dvr.sh ${D}${bindir}

    install -d ${D}${sysconfdir}/lighttpd.d
    install -m 0644 ${WORKDIR}/dvr-http.conf ${D}${sysconfdir}/lighttpd.d/dvr.conf

    install -d ${D}${sysconfdir}/init.d
    install -d ${D}${sysconfdir}/rcS.d
    install -d ${D}${sysconfdir}/rc1.d
    install -d ${D}${sysconfdir}/rc2.d
    install -d ${D}${sysconfdir}/rc3.d
    install -d ${D}${sysconfdir}/rc4.d
    install -d ${D}${sysconfdir}/rc5.d

    install -m 0755 ${WORKDIR}/dvr-init.sh ${D}${sysconfdir}/init.d
    install -m 0755 ${WORKDIR}/dvr-rc.sh ${D}${sysconfdir}/init.d/dvr.sh

    ln -sf ../init.d/dvr-init.sh ${D}${sysconfdir}/rcS.d/S90dvr-init.sh
    ln -sf ../init.d/dvr.sh ${D}${sysconfdir}/rc1.d/K90dvr.sh
    ln -sf ../init.d/dvr.sh ${D}${sysconfdir}/rc2.d/K90dvr.sh
    ln -sf ../init.d/dvr.sh ${D}${sysconfdir}/rc3.d/K90dvr.sh
    ln -sf ../init.d/dvr.sh ${D}${sysconfdir}/rc4.d/K90dvr.sh
    ln -sf ../init.d/dvr.sh ${D}${sysconfdir}/rc5.d/S90dvr.sh
}

FILES_${PN} = "${bindir} ${sysconfdir}"
