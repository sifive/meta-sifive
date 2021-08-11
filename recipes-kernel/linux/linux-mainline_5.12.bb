require recipes-kernel/linux/linux-mainline-common.inc

LINUX_VERSION ?= "5.12.x"
KERNEL_VERSION_SANITY_SKIP="1"

BRANCH = "linux-5.12.y"
SRCREV = "v5.12.18"
SRC_URI = " \
    git://git.kernel.org/pub/scm/linux/kernel/git/stable/linux.git;branch=${BRANCH} \
"

LIC_FILES_CHKSUM = "file://COPYING;md5=6bc538ed5bd9a7fc9398086aedcd7e46"

SRC_URI:append:riscv64 = " \
    file://0001-riscv-sifive-fu740-cpu-1-2-3-4-set-compatible-to-sif.patch \
    file://0002-riscv-sifive-unmatched-update-for-16GB-rev3.patch \
    file://0003-riscv-Add-3-SBI-wrapper-functions-to-get-cpu-manufac.patch \
    file://0004-riscv-Introduce-alternative-mechanism-to-apply-errat.patch \
    file://0005-riscv-sifive-Add-SiFive-alternative-ports.patch \
    file://0006-riscv-sifive-Apply-errata-cip-453-patch.patch \
    file://0007-riscv-sifive-Apply-errata-cip-1200-patch.patch \
    file://0008-riscv-enable-SiFive-errata-CIP-453-and-CIP-1200-Kcon.patch \
    file://0009-clk-sifive-Add-pcie_aux-clock-in-prci-driver-for-PCI.patch \
    file://0010-clk-sifive-Use-reset-simple-in-prci-driver-for-PCIe-.patch \
    file://0011-MAINTAINERS-Add-maintainers-for-SiFive-FU740-PCIe-dr.patch \
    file://0012-dt-bindings-PCI-Add-SiFive-FU740-PCIe-host-controlle.patch \
    file://0013-PCI-fu740-Add-SiFive-FU740-PCIe-host-controller-driv.patch \
    file://0014-riscv-dts-Add-PCIe-support-for-the-SiFive-FU740-C000.patch \
    file://0015-riscv-sifive-unmatched-add-D12-PWM-LED.patch \
    file://0016-riscv-sifive-unmatched-add-gpio-poweroff-node.patch \
    file://0017-riscv-sifive-unmatched-add-D2-RGB-LED.patch \
    file://0018-riscv-sifive-unmatched-remove-A00-from-model.patch \
    file://0019-riscv-sifive-unmatched-define-LEDs-color.patch \
    file://0020-riscv-enable-generic-PCI-resource-mapping.patch \
    file://0021-SiFive-HiFive-Unleashed-Add-PWM-LEDs-D1-D2-D3-D4.patch \
    file://0022-riscv-sifive-unleashed-define-opp-table-cpufreq.patch \
    "

# Use out-of-tree defconfig
SRC_URI:append = " file://defconfig"

unset KBUILD_DEFCONFIG
COMPATIBLE_MACHINE = "freedom-u540|unmatched"

KERNEL_FEATURES:remove = "cfg/fs/vfat.scc"
