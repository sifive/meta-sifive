FILESEXTRAPATHS_prepend := "${THISDIR}/files:"

DEPENDS_append_riscv64 = " opensbi"

SRC_URI_append_freedom-u540 = " \
        file://sifive-fu540-compressed-booti-support.patch \
	file://mmc-boot.txt \
        "

do_compile_prepend_riscv64() {
    export OPENSBI=${DEPLOY_DIR_IMAGE}/fw_dynamic.bin
}
