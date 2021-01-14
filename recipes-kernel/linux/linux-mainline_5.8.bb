require recipes-kernel/linux/linux-mainline-common.inc

LINUX_VERSION ?= "5.8.x"
KERNEL_VERSION_SANITY_SKIP="1"

BRANCH = "linux-5.8.y"
SRCREV = "v5.8.18"
SRC_URI = " \
    git://git.kernel.org/pub/scm/linux/kernel/git/stable/linux.git;branch=${BRANCH} \
"

LIC_FILES_CHKSUM = "file://COPYING;md5=6bc538ed5bd9a7fc9398086aedcd7e46"

SRC_URI_append_freedom-u540 = " \
    file://0001-PCI-microsemi-Add-host-driver-for-Microsemi-PCIe-con.patch \
    file://0002-Microsemi-PCIe-expansion-board-DT-entry.patch \
    file://0003-HACK-Revert-of-device-Really-only-set-bus-DMA-mask-w.patch \
    file://0004-SiFive-Unleashed-CPUFreq.patch \
    file://0007-Add-PWM-LEDs-D1-D2-D3-D4.patch \
    file://irqchip-sifive-plic-fix-broken-irq_set_affinity-callback.patch \
    file://irqchip-sifive-plix-fix-getting-wrong-chip_date-when-interrupt-is-hierarchy.patch \
    file://i2c-ocores-fix-polling-mode-workaround-on-FU540-C000-SoC.patch \
    file://riscv-fixup-CONFIG_GENERIC_TIME_VSYSCALL.patch \
"

# For freedom-u540 use out-of-tree defconfig
# The upstream defconfig might contain a large number of debug options, which
# could affect the performance.
SRC_URI_append_freedom-u540 = " file://defconfig"

unset KBUILD_DEFCONFIG_qemuriscv64
unset KBUILD_DEFCONFIG_freedom-u540
