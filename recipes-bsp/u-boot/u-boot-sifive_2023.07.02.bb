require recipes-bsp/u-boot/u-boot-common.inc
require recipes-bsp/u-boot/u-boot.inc

PROVIDES += "u-boot"

DEPENDS += "bc-native dtc-native"

FILESEXTRAPATHS:prepend := "${THISDIR}/files:"

DEPENDS:append:riscv64 = " opensbi"

SRC_URI:append:riscv64 = " \
    file://0002-board-sifive-spl-Initialized-the-PWM-setting-in-the-.patch \
    file://0003-board-sifive-Set-LED-s-color-to-purple-in-the-U-boot.patch \
    file://0004-board-sifive-Set-LED-s-color-to-blue-before-jumping-.patch \
    file://0005-board-sifive-spl-Set-remote-thermal-of-TMP451-to-85-.patch \
    file://0008-riscv-dts-Add-few-PMU-events.patch \
    file://0006-riscv-sifive-fu740-reduce-DDR-speed-from-1866MT-s-to.patch \
    "

# Only add opensbi dependency if opensbi is in image deps
do_compile[depends] += "opensbi:do_deploy"

do_compile:prepend:riscv64() {
    export OPENSBI=${DEPLOY_DIR_IMAGE}/fw_dynamic.bin
}

PROVIDES += "u-boot"

COMPATIBLE_MACHINE = "(freedom-u540|qemuriscv64|unmatched)"
