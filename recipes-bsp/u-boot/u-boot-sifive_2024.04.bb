require recipes-bsp/u-boot/u-boot-common.inc
require recipes-bsp/u-boot/u-boot.inc

PROVIDES += "u-boot"

DEPENDS += "bc-native dtc-native"

FILESEXTRAPATHS:prepend := "${THISDIR}/files:"

DEPENDS:append:riscv64 = " opensbi"

SRCREV = "25049ad560826f7dc1c4740883b0016014a59789"

SRC_URI = "git://source.denx.de/u-boot/u-boot.git;protocol=https;branch=master"

SRC_URI:append:riscv64 = " \
    file://0005-riscv-dts-Add-few-PMU-events.patch \
    "

# Only add opensbi dependency if opensbi is in image deps
do_compile[depends] += "opensbi:do_deploy"

do_compile:prepend:riscv64() {
    export OPENSBI=${DEPLOY_DIR_IMAGE}/fw_dynamic.bin
}

COMPATIBLE_MACHINE = "(freedom-u540|qemuriscv64|unmatched)"
