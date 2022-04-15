require recipes-kernel/linux/linux-mainline-common.inc

LINUX_VERSION ?= "5.17.x"
KERNEL_VERSION_SANITY_SKIP="1"

BRANCH = "linux-5.17.y"
SRCREV = "v5.17.2"
SRC_URI = " \
    git://git.kernel.org/pub/scm/linux/kernel/git/stable/linux.git;branch=${BRANCH} \
"

LIC_FILES_CHKSUM = "file://COPYING;md5=6bc538ed5bd9a7fc9398086aedcd7e46"

SRC_URI:append:riscv64 = " \
    file://0001-riscv-sifive-fu740-cpu-1-2-3-4-set-compatible-to-sif.patch \
    file://drop_gpios.patch \
    file://relocate_dtb.patch \
    file://workaround_asm2824.patch \
    file://0003-riscv-sifive-unmatched-define-PWM-LEDs.patch \
    file://0005-SiFive-HiFive-Unleashed-Add-PWM-LEDs-D1-D2-D3-D4.patch \
    file://Revert-riscv-dts-sifive-unmatched-Link-the-tmp451-wi.patch \
    file://0006-riscv-sifive-unleashed-define-opp-table-cpufreq.patch \
    "

# Use out-of-tree defconfig
SRC_URI:append = " file://defconfig"

unset KBUILD_DEFCONFIG
COMPATIBLE_MACHINE = "freedom-u540|unmatched|qemuriscv64|qemuriscv64_b|qemuriscv64_b_zfh|qemuriscv64_v"

KERNEL_FEATURES:remove = "cfg/fs/vfat.scc"

do_shared_workdir:append:riscv64 () {
    if [ -e arch/${ARCH}/kernel/vdso/vdso.lds ]; then
        mkdir -p $kerneldir/arch/${ARCH}/kernel/vdso
        cp arch/${ARCH}/kernel/vdso/vdso.lds $kerneldir/arch/${ARCH}/kernel/vdso/vdso.lds
    fi
}
