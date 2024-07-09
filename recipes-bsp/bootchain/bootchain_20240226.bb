inherit deploy
inherit allarch

LICENSE = "CLOSED"

BRANCH = "master"
SRCREV = "ddda1d0290cb88c3fa64d70ea65d6df7f2d56376"
SRC_URI = "git://git@github.com/sifive/hifive-premier-p550-tools.git;branch=${BRANCH};protocol=ssh"

SRC_URI:append:hifive-premier-p550 = " file://nsign.cfg"

S = "${WORKDIR}"

INHIBIT_DEFAULT_DEPS = "1"
INHIBIT_PACKAGE_DEBUG_SPLIT = "1"

do_configure[noexec] = "1"
do_install[noexec] = "1"

DEPENDS:append:hifive-premier-p550 = " ddr-fw second-boot-fw opensbi-sifive-hf-prem"

do_compile[depends] += " ddr-fw:do_deploy second-boot-fw:do_deploy opensbi-sifive-hf-prem:do_deploy"

do_compile() {
	install -m 755 ${WORKDIR}/git/nsign/nsign ${WORKDIR}
	install -m 755 ${DEPLOY_DIR_IMAGE}/ddr_fw/ddr5_fw.bin ${WORKDIR}/
	install -m 755 ${DEPLOY_DIR_IMAGE}/second_boot_fw/second_boot_fw.bin ${WORKDIR}/
	install -m 755 ${DEPLOY_DIR_IMAGE}/fw_payload.bin ${WORKDIR}/
	cd ${WORKDIR} && ./nsign ./nsign.cfg
}

do_deploy() {
	install -m 755 ${WORKDIR}/bootloader_ddr5_secboot.bin ${DEPLOYDIR}/
}

INSANE_SKIP = "arch"

addtask deploy after do_install

COMPATIBLE_MACHINE = "(hifive-premier-p550)"
