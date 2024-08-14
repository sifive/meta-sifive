SUMMARY = "Second Boot firmware used in HiFive-Premier-P550 Board"

inherit deploy
inherit autotools-brokensep

LICENSE = "CLOSED"

INHIBIT_DEFAULT_DEPS = "1"
INHIBIT_PACKAGE_DEBUG_SPLIT = "1"

BRANCH = "master"
SRCREV = "b115a84c772392011fba776e59d1fe8db7fb36bf"
SRC_URI = "git://git@github.com/sifive/hifive-premier-p550-tools.git;branch=${BRANCH};protocol=ssh"

S = "${WORKDIR}/git"

do_configure[noexec] = "1"
do_compile[noexec] = "1"
do_install[noexec] = "1"

do_deploy () {
    install -d ${DEPLOYDIR}/second_boot_fw
    install -m 755 ${S}/second_boot_fw/second_boot_fw.bin ${DEPLOYDIR}/second_boot_fw
}

addtask deploy after do_install

COMPATIBLE_MACHINE = "(hifive-premier-p550)"
