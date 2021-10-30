SUMMARY = "System load testing utility"
DESCRIPTION = "Deliberately simple workload generator for POSIX systems. It \
imposes a configurable amount of CPU, memory, I/O, and disk stress on the system."
HOMEPAGE = "https://kernel.ubuntu.com/~cking/stress-ng/"
LICENSE = "GPLv2"
LIC_FILES_CHKSUM = "file://COPYING;md5=b234ee4d69f5fce4486a80fdaf4a4263"

SRC_URI = "http://ftp.debian.org/debian/pool/main/s/stress-ng/stress-ng_${PV}.orig.tar.xz \
           file://0001-Do-not-preserve-ownership-when-installing-example-jo.patch \
           "
SRC_URI[sha256sum] = "f058c8fba37596ab32c3a4b2aedbdbf5f2b8a8ba1d312059e733290543ad00ac"

DEPENDS = "coreutils-native zlib libaio libbsd attr libcap libgcrypt keyutils lksctp-tools"

PROVIDES = "stress"
RPROVIDES:${PN} = "stress"
RREPLACES:${PN} = "stress"
RCONFLICTS:${PN} = "stress"

inherit bash-completion

do_install() {
    oe_runmake DESTDIR=${D} install
    ln -s stress-ng ${D}${bindir}/stress
}

