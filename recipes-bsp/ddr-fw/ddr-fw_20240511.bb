inherit deploy
inherit allarch

LICENSE = "CLOSED"

BRANCH = "master"
SRCREV = "43ec142e5a9d95820b0a06e51863931229e6f71b"
SRC_URI = "git://git@github.com/sifive/hifive-premier-p550-tools.git;branch=${BRANCH};protocol=ssh"

S = "${WORKDIR}/git"

CLEANBROKEN = "1"
INHIBIT_DEFAULT_DEPS = "1"
INHIBIT_PACKAGE_DEBUG_SPLIT = "1"

do_configure[noexec] = "1"
do_compile[noexec] = "1"
do_install[noexec] = "1"

do_deploy () {
	install -d ${DEPLOYDIR}/ddr_fw
	install -m 755 ${S}/ddr-fw/ddr_fw.bin ${DEPLOYDIR}/ddr_fw/
}

addtask deploy after do_install

COMPATIBLE_MACHINE = "(hifive-premier-p550)"
