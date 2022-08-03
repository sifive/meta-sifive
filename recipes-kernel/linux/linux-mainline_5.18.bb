require recipes-kernel/linux/linux-mainline-common.inc

LIC_FILES_CHKSUM = "file://COPYING;md5=6bc538ed5bd9a7fc9398086aedcd7e46"

LINUX_VERSION = "5.18.15"
KERNEL_VERSION_SANITY_SKIP = "1"

KBRANCH = "linux-5.18.y"

SRCREV = "3740a5da82ebec7a6d8f3a6deea77b8129c8c2ee"

SRC_URI = "git://git.kernel.org/pub/scm/linux/kernel/git/stable/linux.git;branch=${KBRANCH}"

SRC_URI:append:riscv64 = " \
    file://0001-riscv-sifive-fu740-cpu-1-2-3-4-set-compatible-to-sif.patch \
    file://0003-riscv-sifive-unmatched-define-PWM-LEDs.patch \
    file://0005-SiFive-HiFive-Unleashed-Add-PWM-LEDs-D1-D2-D3-D4.patch \
    file://Revert-riscv-dts-sifive-unmatched-Link-the-tmp451-wi.patch \
"
