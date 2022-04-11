FILESEXTRAPATHS:prepend := "${THISDIR}/files:"

DEPENDS:append:riscv64 = " opensbi"

SRC_URI:append:riscv64 = " \
    file://0001-riscv-sifive-unleashed-support-compressed-images.patch \
    file://0002-board-sifive-spl-Initialized-the-PWM-setting-in-the-.patch \
    file://0003-board-sifive-Set-LED-s-color-to-purple-in-the-U-boot.patch \
    file://0004-board-sifive-Set-LED-s-color-to-blue-before-jumping-.patch \
    file://0005-board-sifive-spl-Set-remote-thermal-of-TMP451-to-85-.patch \
    file://0006-riscv-sifive-unmatched-leave-128MiB-for-ramdisk.patch \
    file://0007-riscv-sifive-unmatched-disable-FDT-and-initrd-reloca.patch \
    "

SRC_URI:append:freedom-u540 = " \
    file://mmc-boot.txt \
"

do_compile:prepend:riscv64() {
    export OPENSBI=${DEPLOY_DIR_IMAGE}/fw_dynamic.bin
}

do_configure[depends] += "opensbi:do_deploy"
