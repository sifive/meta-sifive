require u-boot-common.inc
require u-boot.inc

DEPENDS += "bc-native dtc-native python3-setuptools-native"

DEPENDS_append_riscv64 = " opensbi"

SRC_URI_append_freedom-u540 = " \
        file://sifive-fu540-compressed-booti-support.patch \
        file://mmc-boot.txt \
"

SRC_URI_append_unmatched = " \
    file://0001-clk-sifive-fu540-prci-Extract-prci-core-to-common-ba.patch \
    file://0002-clk-sifive-fu540-prci-Use-common-name-for-prci-confi.patch \
    file://0003-clk-sifive-fu740-Sync-up-DT-bindings-header-with-ups.patch \
    file://0004-clk-sifive-fu740-prci-Add-a-driver-for-the-SiFive-FU.patch \
    file://0005-clk-sifive-select-PLL-clock-as-input-source-after-en.patch \
    file://0006-clk-sifive-fu740-prci-set-HFPCLKPLL-rate-to-260-Mhz.patch \
    file://0007-riscv-dts-Add-SiFive-FU740-C000-SoC-dts-from-Linux.patch \
    file://0008-riscv-dts-Add-hifive-unmatched-a00-dts-from-Linux.patch \
    file://0009-riscv-cpu-fu740-Add-support-for-cpu-fu740.patch \
    file://0010-riscv-Add-SiFive-HiFive-Unmatched-FU740-board-suppor.patch \
    file://0011-riscv-sifive-dts-fu740-Add-board-u-boot.dtsi-files.patch \
    file://0012-dt-bindings-sifive-fu740-add-indexes-for-reset-signa.patch \
    file://0013-fu740-dtsi-add-reset-producer-and-consumer-entries.patch \
    file://0014-ram-sifive-Add-common-DDR-driver-for-sifive.patch \
    file://0015-ram-sifive-Added-compatible-string-for-FU740-c000-dd.patch \
    file://0016-sifive-dts-fu740-Add-DDR-controller-and-phy-register.patch \
    file://0017-riscv-sifive-dts-fu740-add-U-Boot-dmc-node.patch \
    file://0018-riscv-sifive-hifive_unmatched_fu740-add-SPL-configur.patch \
    file://0019-sifive-hifive_unmatched_fu740-Add-sample-SD-gpt-part.patch \
    file://0020-sifive-fu740-Add-U-Boot-proper-sector-start.patch \
    file://0021-configs-hifive_unmatched_fu740-Add-config-options-fo.patch \
    file://0022-riscv-sifive-fu540-enable-all-cache-ways-from-U-Boot.patch \
    file://0023-riscv-sifive-dts-fu740-set-ethernet-clock-rate.patch \
    file://0024-sifive-fu740-set-kernel_comp_addr_r-and-kernel_comp_.patch \
    file://0025-sifive-fu740-enable-full-L2-cache-ways-16-ways-total.patch \
    file://0026-sifive-fu740-fix-cache-controller-signals-order.patch \
    file://0027-sifive-fu740-change-eth0-assigned-clock-rates-to-125.patch \
    file://0028-sifive-hifive_unmatched_fu740-Enable-64bit-PCI-resou.patch \
    file://0029-clk-sifive-add-pciaux-clock.patch \
    file://0030-pci-sifive-add-pcie-driver-for-fu740.patch \
    file://0031-Update-SiFive-Unmatched-defconfig.patch \
    file://0032-riscv-sifive-unmatched-set-cacheline-size-to-64-byte.patch \
    file://0033-fu740-add-CONFIG_CMD_GPT-and-CONFIG_CMD_GPT_RENAME.patch \
    file://0034-Unmathced-FU740-add-NVME-USB-and-PXE-to-boot-targets.patch \
    file://0035-riscv-clear-feature-disable-CSR.patch \
    file://0036-riscv-sifive-unmatched-add-I2C-EEPROM.patch \
    file://0037-cmd-Add-a-pwm-command.patch \
    file://0038-cmd-pwm-Rework-argc-sanity-checking.patch \
    file://0039-riscv-sifive-unmatched-enable-PWM.patch \
    file://0040-riscv-sifive-unmatched-update-for-rev3-16GB-1866.patch \
    file://0041-Fix-CRC32-checksum-for-SiFive-HiFive-EEPROM.patch \
"

do_compile_prepend_riscv64() {
    export OPENSBI=${DEPLOY_DIR_IMAGE}/fw_dynamic.bin
}
