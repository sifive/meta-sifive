inherit deploy
inherit allarch

LICENSE = "CLOSED"

BRANCH = "master"
SRCREV = "4c8736f9be8e5cb91009ced5794584d4fb7db97f"
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
	install -m 755 ${S}/ddr-fw/ddr5_fw.bin ${DEPLOYDIR}/ddr_fw
}

addtask deploy after do_install

COMPATIBLE_MACHINE = "(hifive-premier-p550)"
