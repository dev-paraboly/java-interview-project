set -e

curl https://raw.githubusercontent.com/OpenAPITools/openapi-generator/master/bin/utils/openapi-generator-cli.sh > /tmp/openapi-generator-cli

chmod u+x /tmp/openapi-generator-cli

OPENAPI_GENERATOR_VERSION=4.2.3 /tmp/openapi-generator-cli version
tmp_directory=$(mktemp -d)

OPENAPI_GENERATOR_VERSION=4.2.3 /tmp/openapi-generator-cli generate -i api-docs.json -g javascript -o $tmp_directory --additional-properties usePromises=true,useE6=true --skip-validate-spec
cd $tmp_directory
npm i
npm pack
xdg-open .
echo $tmp_directory
