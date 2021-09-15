FILESEXTRAPATHS:prepend := "${THISDIR}/files:"

DEPENDS:append:riscv64 = " opensbi"

SRC_URI:append:riscv64 = " \
    file://0001-riscv-sifive-unleashed-support-compressed-images.patch \
    file://0002-drivers-clk-sifive-fu740-prci-replace-pciaux-with-pc.patch \
    file://0003-board-sifive-unmatched-add-initial-support-for-a-pla.patch \
    file://0004-riscv-sifive-fu740-kconfig-Enable-support-for-Openco.patch \
    file://0005-riscv-sifive-fu740-Support-i2c-in-spl.patch \
    file://0006-board-sifive-Add-an-interface-to-get-PCB-revision.patch \
    file://0007-board-sifive-remove-the-command-for-setting-serial-n.patch \
    file://0008-board-sifive-unmatched-refine-GEMGXL-initialized-fun.patch \
    file://0009-board-sifive-unmatched-reset-USB-hub-PCIe-USB-bridge.patch \
    file://0010-board-sifive-spl-Initialized-the-PWM-setting-in-the-.patch \
    file://0011-board-sifive-Set-LED-s-color-to-purple-in-the-U-boot.patch \
    file://0012-board-sifive-Set-LED-s-color-to-blue-before-jumping-.patch \
    file://0013-board-sifive-spl-Set-remote-thermal-of-TMP451-to-85-.patch \
    file://0014-riscv-sifive-Set-default-fdtfile-names.patch \
    file://0015-riscv-sifive-unmatched-leave-128MiB-for-ramdisk.patch \
    file://0016-riscv-sifive-unmatched-disable-FDT-and-initrd-reloca.patch \
    "

SRC_URI:append:freedom-u540 = " \
    file://mmc-boot.txt \
"

do_compile:prepend:riscv64() {
    export OPENSBI=${DEPLOY_DIR_IMAGE}/fw_dynamic.bin
}

do_configure[depends] += "opensbi:do_deploy"
