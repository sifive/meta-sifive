require libunwind.inc

SRCREV = "97d74d396264720e86d4b8455216f4a7b06a54af"
PV = "20211019+git${SRCPV}"

SRC_URI = "git://github.com/libunwind/libunwind \
           "
SRC_URI:append:libc-musl = " file://musl-header-conflict.patch"

S = "${WORKDIR}/git"

UPSTREAM_CHECK_COMMITS = "1"

EXTRA_OECONF:append:libc-musl = " --disable-documentation --disable-tests --enable-static"

# http://errors.yoctoproject.org/Errors/Details/20487/
ARM_INSTRUCTION_SET:armv4 = "arm"
ARM_INSTRUCTION_SET:armv5 = "arm"

COMPATIBLE_HOST:riscv32 = "null"

LDFLAGS += "-Wl,-z,relro,-z,now ${@bb.utils.contains('DISTRO_FEATURES', 'ld-is-gold', ' -fuse-ld=bfd ', '', d)}"

SECURITY_LDFLAGS:append:libc-musl = " -lssp_nonshared"
CACHED_CONFIGUREVARS:append:libc-musl = " LDFLAGS='${LDFLAGS} -lucontext'"
