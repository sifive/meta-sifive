inherit deploy
inherit allarch

LICENSE = "CLOSED"

SRC_URI:append:hifive-premier-p550 = " file://nsign.cfg"

S = "${WORKDIR}"

INHIBIT_DEFAULT_DEPS = "1"
INHIBIT_PACKAGE_DEBUG_SPLIT = "1"

do_configure[noexec] = "1"
do_install[noexec] = "1"

DEPENDS:append:hifive-premier-p550 = " ddr-fw second-boot-fw opensbi-sifive-hf-prem u-boot nsign-native"

do_compile[depends] += " ddr-fw:do_deploy second-boot-fw:do_deploy opensbi-sifive-hf-prem:do_deploy u-boot:do_compile"

do_compile() {
	install -m 755 ${DEPLOY_DIR_IMAGE}/ddr_fw/ddr5_fw.bin ${WORKDIR}/
	install -m 755 ${DEPLOY_DIR_IMAGE}/second_boot_fw/second_boot_fw.bin ${WORKDIR}/
	install -m 755 ${DEPLOY_DIR_IMAGE}/fw_payload.bin ${WORKDIR}/
	cd ${WORKDIR} && nsign ./nsign.cfg
}

do_deploy() {
	install -m 755 ${WORKDIR}/bootloader_ddr5_secboot.bin ${DEPLOYDIR}/
}

INSANE_SKIP = "arch"

addtask deploy after do_install

COMPATIBLE_MACHINE = "(hifive-premier-p550)"
