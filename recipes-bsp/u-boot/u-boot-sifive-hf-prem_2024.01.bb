require recipes-bsp/u-boot/u-boot-common.inc
require recipes-bsp/u-boot/u-boot.inc

PROVIDES += "u-boot"

DEPENDS += "bc-native dtc-native"

SRCREV = "d7d4b505ebc2e54b579cbd7a591c481228d20018"
SRC_URI = "git://github.com/eswincomputing/u-boot.git;protocol=https;branch=u-boot-2024.01-EIC7X"

SRC_URI:append = " \
	file://0001-perf-change-relocaddr-to-lower-4G.patch \
	file://0002-WIN2030-15838-fix-fix-the-issue-of-memalign-fail.patch \
	"

do_deploy:append () {
	install -m 755 ${B}/u-boot.dtb ${DEPLOYDIR}
}

COMPATIBLE_MACHINE = "hifive-premier-p550"
