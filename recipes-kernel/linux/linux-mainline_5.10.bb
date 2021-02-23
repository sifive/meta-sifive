require recipes-kernel/linux/linux-mainline-common.inc

LINUX_VERSION ?= "5.10.x"
KERNEL_VERSION_SANITY_SKIP="1"

BRANCH = "linux-5.10.y"
SRCREV = "v5.10.17"
SRC_URI = " \
    git://git.kernel.org/pub/scm/linux/kernel/git/stable/linux.git;branch=${BRANCH} \
"

SRC_append_unmatched = " \
    file://riscv-fixup-CONFIG_GENERIC_TIME_VSYSCALL.patch \
    file://0001-clk-sifive-Extract-prci-core-to-common-base.patch \
    file://0002-clk-sifive-Use-common-name-for-prci-configuration.patch \
    file://0003-clk-sifive-Add-a-driver-for-the-SiFive-FU740-PRCI-IP.patch
    file://0004-clk-sifive-Fix-the-wrong-bit-field-shift.patch \
    file://0005-clk-sifive-Add-clock-enable-and-disable-ops.patch \
    file://0006-dt-bindings-riscv-Update-DT-binding-docs-to-support-.patch \
    file://0007-dt-bindings-spi-Update-DT-binding-docs-to-support-Si.patch \
    file://0008-dt-bindings-pwm-Update-DT-binding-docs-to-support-Si.patch \
    file://0009-dt-bindings-serial-Update-DT-binding-docs-to-support.patch \
    file://0010-dt-bindings-gpio-Update-DT-binding-docs-to-support-S.patch \
    file://0011-dt-bindings-i2c-Update-DT-binding-docs-to-support-Si.patch \
    file://0012-riscv-dts-add-initial-support-for-the-SiFive-FU740-C.patch \
    file://0013-dt-bindings-riscv-Update-YAML-doc-to-support-SiFive-.patch \
    file://0014-riscv-dts-add-initial-board-data-for-the-SiFive-HiFi.patch \
    file://0015-dt-bindings-riscv-Update-l2-cache-DT-documentation-t.patch \
    file://0016-RISC-V-sifive_l2_cache-Update-L2-cache-driver-to-sup.patch \
    file://0017-gpio-sifive-To-get-gpio-irq-offset-from-device-tree-.patch \
    file://0018-riscv-Add-3-SBI-wrapper-functions-to-get-cpu-manufac.patch \
    file://0019-riscv-Get-CPU-manufactory-information.patch \
    file://0020-riscv-Introduce-alternative-mechanism-to-apply-errat.patch \
    file://0021-riscv-sifive-apply-errata-cip-453-patch.patch \
    file://0022-riscv-dts-fu740-fix-cache-controller-interrupts.patch \
    file://0023-riscv-sifive-fu740-cpu-1-2-3-4-set-compatible-to-sif.patch \
    file://0024-riscv-dts-Add-PCIe-support-for-the-SiFive-FU740-C000.patch \
    file://0025-clk-sifive-Add-pcie_aux-clock-in-prci-driver-for-PCI.patch \
    file://0026-clk-sifive-Use-reset-simple-in-prci-driver-for-PCIe-.patch \
    file://0027-MAINTAINERS-Add-maintainers-for-SiFive-FU740-PCIe-dr.patch \
    file://0028-dt-bindings-PCI-Add-SiFive-FU740-PCIe-host-controlle.patch \
    file://0029-PCI-designware-Add-SiFive-FU740-PCIe-host-controller.patch \
"

LIC_FILES_CHKSUM = "file://COPYING;md5=6bc538ed5bd9a7fc9398086aedcd7e46"

# For unmatched use out-of-tree defconfig
SRC_URI_append_unmatched = " file://defconfig"

unset KBUILD_DEFCONFIG_unmatched
COMPATIBLE_MACHINE = "unmatched"
