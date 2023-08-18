SUMMARY = "DVR application"

LICENSE = "MIT"
LIC_FILES_CHKSUM = "file://COPYING;md5=71bec9001bfc79fcaa9f0024c35e8cdd"

DEPENDS = ""
RDEPENDS:${PN} = "connman-client"

SRC_URI = "file://dvr-start.sh \
           file://dvr-mkst.sh \
           file://COPYING \
          "

S = "${WORKDIR}"

do_configure() {
}

do_compile() {
}

do_install() {
    install -d ${D}${sysconfdir}/init.d
    install -m 0755 ${WORKDIR}/dvr-start.sh ${D}${sysconfdir}/init.d
    install -d ${D}${bindir}
    install -m 0755 ${WORKDIR}/dvr-mkst.sh ${D}${bindir}
}

FILES_${PN} = "${bindir} ${sysconfdir}"
