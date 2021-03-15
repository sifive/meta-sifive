require recipes-kernel/linux/linux-mainline-common.inc

LINUX_VERSION ?= "5.11.x"
KERNEL_VERSION_SANITY_SKIP="1"

BRANCH = "linux-5.11.y"
SRCREV = "v5.11.5"
SRC_URI = " \
    git://git.kernel.org/pub/scm/linux/kernel/git/stable/linux.git;branch=${BRANCH} \
"

LIC_FILES_CHKSUM = "file://COPYING;md5=6bc538ed5bd9a7fc9398086aedcd7e46"

SRC_URI_append_unmatched = " \
    file://0001-dt-bindings-riscv-Update-DT-binding-docs-to-support-.patch \
    file://0002-dt-bindings-pwm-Update-DT-binding-docs-to-support-Si.patch \
    file://0003-dt-bindings-gpio-Update-DT-binding-docs-to-support-S.patch \
    file://0004-riscv-dts-add-initial-support-for-the-SiFive-FU740-C.patch \
    file://0005-dt-bindings-riscv-Update-YAML-doc-to-support-SiFive-.patch \
    file://0006-riscv-dts-add-initial-board-data-for-the-SiFive-HiFi.patch \
    file://0007-dt-bindings-riscv-Update-l2-cache-DT-documentation-t.patch \
    file://0008-RISC-V-sifive_l2_cache-Update-L2-cache-driver-to-sup.patch \
    file://0009-riscv-dts-fu740-fix-cache-controller-interrupts.patch \
    file://0010-riscv-sifive-fu740-cpu-1-2-3-4-set-compatible-to-sif.patch \
    file://0011-riscv-sifive-unmatched-update-for-16GB-rev3.patch \
    file://0012-riscv-Add-3-SBI-wrapper-functions-to-get-cpu-manufac.patch \
    file://0013-riscv-Get-CPU-manufacturer-information.patch \
    file://0014-riscv-Introduce-alternative-mechanism-to-apply-errat.patch \
    file://0015-riscv-sifive-apply-errata-cip-453-patch.patch \
    file://0016-clk-sifive-Add-pcie_aux-clock-in-prci-driver-for-PCI.patch \
    file://0017-clk-sifive-Use-reset-simple-in-prci-driver-for-PCIe-.patch \
    file://0018-MAINTAINERS-Add-maintainers-for-SiFive-FU740-PCIe-dr.patch \
    file://0019-dt-bindings-PCI-Add-SiFive-FU740-PCIe-host-controlle.patch \
    file://0020-PCI-designware-Add-SiFive-FU740-PCIe-host-controller.patch \
    file://0021-riscv-dts-Add-PCIe-support-for-the-SiFive-FU740-C000.patch \
"

# For unmatched use out-of-tree defconfig
SRC_URI_append_unmatched = " file://defconfig"

unset KBUILD_DEFCONFIG_unmatched
COMPATIBLE_MACHINE = "unmatched"
