require libunwind.inc

SRCREV = "1527061fc5ce7cfd3911ac7ee8af04fdd4393477"
PV = "20210810+git${SRCPV}"

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
