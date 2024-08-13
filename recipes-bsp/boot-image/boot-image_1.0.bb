SUMMARY = "To generate vfat image including kernel, DTB and extlinux.conf"
DESCRIPTION = "Recipe to generate boot.vfat image containing kernel, DTB and extlinux.conf that can be flashed to HiFive Premier P550 board using fastboot tool"

LICENSE = "CLOSED"

DEPENDS:hifive-premier-p550 = "mtools-native"
do_install[depends] = "virtual/kernel:do_deploy"

do_install() {
	install -d ${DEPLOY_DIR_IMAGE}/extlinux
	install -m 0755 "${COREBASE}/../${FILE_LAYERNAME}/scripts/lib/wic/canned-wks/${MACHINE}-emmc-extlinux.conf" "${DEPLOY_DIR_IMAGE}/extlinux/extlinux.conf"
	dd if=/dev/zero of=${DEPLOY_DIR_IMAGE}/boot.vfat bs=1M count=512
	mformat -Fi ${DEPLOY_DIR_IMAGE}/boot.vfat ::
	mcopy -i ${DEPLOY_DIR_IMAGE}/boot.vfat ${DEPLOY_DIR_IMAGE}/Image.gz \
	    ${DEPLOY_DIR_IMAGE}/extlinux/ \
	    ${DEPLOY_DIR_IMAGE}/hifive-premier-p550.dtb ::
}
