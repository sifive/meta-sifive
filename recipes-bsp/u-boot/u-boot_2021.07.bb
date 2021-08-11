require u-boot-common.inc
require u-boot.inc

SRC_URI:append = " file://0001-riscv32-Use-double-float-ABI-for-rv32.patch"

DEPENDS += "bc-native dtc-native python3-setuptools-native"

DEPENDS:append:riscv64 = " opensbi"

SRC_URI:append:freedom-u540 = " \
    file://sifive-fu540-compressed-booti-support.patch \
    file://mmc-boot.txt \
"

SRC_URI:append:unmatched = " \
    file://0001-drivers-clk-sifive-fu740-prci-replace-pciaux-with-pc.patch \
    file://0002-board-sifive-unmatched-add-initial-support-for-a-pla.patch \
    file://0003-riscv-sifive-fu740-kconfig-Enable-support-for-Openco.patch \
    file://0004-riscv-sifive-fu740-Support-i2c-in-spl.patch \
    file://0005-board-sifive-Add-an-interface-to-get-PCB-revision.patch \
    file://0006-board-sifive-remove-the-command-for-setting-serial-n.patch \
    file://0007-board-sifive-unmatched-refine-GEMGXL-initialized-fun.patch \
    file://0008-board-sifive-unmatched-reset-USB-hub-PCIe-USB-bridge.patch \
    file://0009-board-sifive-spl-Initialized-the-PWM-setting-in-the-.patch \
    file://0010-board-sifive-Set-LED-s-color-to-purple-in-the-U-boot.patch \
    file://0011-board-sifive-Set-LED-s-color-to-blue-before-jumping-.patch \
    file://0012-board-sifive-spl-Set-remote-thermal-of-TMP451-to-85-.patch \
    file://0013-riscv-sifive-Set-default-fdtfile-names.patch \
    "

do_compile:prepend:riscv64() {
    export OPENSBI=${DEPLOY_DIR_IMAGE}/fw_dynamic.bin
}

do_configure[depends] += "opensbi:do_deploy"
