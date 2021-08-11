require recipes-kernel/linux/linux-mainline-common.inc

LINUX_VERSION ?= "5.13.x"
KERNEL_VERSION_SANITY_SKIP="1"

BRANCH = "linux-5.13.y"
SRCREV = "v5.13.9"
SRC_URI = " \
    git://git.kernel.org/pub/scm/linux/kernel/git/stable/linux.git;branch=${BRANCH} \
"

LIC_FILES_CHKSUM = "file://COPYING;md5=6bc538ed5bd9a7fc9398086aedcd7e46"

SRC_URI:append:riscv64 = " \
    file://0001-riscv-sifive-fu740-cpu-1-2-3-4-set-compatible-to-sif.patch \
    file://0002-riscv-sifive-unmatched-update-for-16GB-rev3.patch \
    file://0003-riscv-sifive-unmatched-add-D12-PWM-LED.patch \
    file://0004-riscv-sifive-unmatched-add-gpio-poweroff-node.patch \
    file://0005-riscv-sifive-unmatched-add-D2-RGB-LED.patch \
    file://0006-riscv-sifive-unmatched-remove-A00-from-model.patch \
    file://0007-riscv-sifive-unmatched-define-LEDs-color.patch \
    file://0008-riscv-enable-generic-PCI-resource-mapping.patch \
    file://0009-SiFive-HiFive-Unleashed-Add-PWM-LEDs-D1-D2-D3-D4.patch \
    file://0010-riscv-sifive-unleashed-define-opp-table-cpufreq.patch \
    "

# Use out-of-tree defconfig
SRC_URI:append = " file://defconfig"

unset KBUILD_DEFCONFIG
COMPATIBLE_MACHINE = "freedom-u540|unmatched"

KERNEL_FEATURES:remove = "cfg/fs/vfat.scc"
