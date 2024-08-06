require recipes-bsp/u-boot/u-boot-common.inc
require recipes-bsp/u-boot/u-boot.inc

PROVIDES += "u-boot"

DEPENDS += "bc-native dtc-native"

SRCREV = "d5e2187d8e0e252aece81d0dd250007761a66307"
SRC_URI = "git://github.com/eswincomputing/u-boot.git;protocol=https;branch=u-boot-2024.01-EIC7X"

do_deploy:append () {
	install -m 755 ${B}/u-boot.dtb ${DEPLOYDIR}
}

COMPATIBLE_MACHINE = "hifive-premier-p550"
