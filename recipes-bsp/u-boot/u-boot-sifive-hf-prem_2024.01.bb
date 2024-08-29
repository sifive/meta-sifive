require recipes-bsp/u-boot/u-boot-common.inc
require recipes-bsp/u-boot/u-boot.inc

PROVIDES += "u-boot"

DEPENDS += "bc-native dtc-native"

SRCREV = "7a94e3174e14c5bf48db0f7a5fc253c9f06a4760"
SRC_URI = "git://github.com/eswincomputing/u-boot.git;protocol=https;branch=u-boot-2024.01-EIC7X \
           file://0001-riscv-hifive_premier_p550-defined-boot-media-sequenc.patch \
           file://0001-eswin-get-ddr-size-and-pass-to-kernel.patch"

do_deploy:append () {
	install -m 755 ${B}/u-boot.dtb ${DEPLOYDIR}
}

COMPATIBLE_MACHINE = "hifive-premier-p550"
