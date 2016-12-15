/*
 *  Copyright (c) 2016, WSO2 Inc. (http://www.wso2.org) All Rights Reserved.
 *
 *  WSO2 Inc. licenses this file to you under the Apache License,
 *  Version 2.0 (the "License"); you may not use this file except
 *  in compliance with the License.
 *  You may obtain a copy of the License at
 *
 *  http://www.apache.org/licenses/LICENSE-2.0
 *
 *  Unless required by applicable law or agreed to in writing,
 *  software distributed under the License is distributed on an
 *  "AS IS" BASIS, WITHOUT WARRANTIES OR CONDITIONS OF ANY
 *  KIND, either express or implied.  See the License for the
 *  specific language governing permissions and limitations
 *  under the License.
 */

package org.wso2.ballerina.core.nativeimpl.lang.message;

import org.osgi.service.component.annotations.Component;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.wso2.ballerina.core.interpreter.Context;
import org.wso2.ballerina.core.model.types.TypeEnum;
import org.wso2.ballerina.core.model.values.BValue;
import org.wso2.ballerina.core.model.values.MessageValue;
import org.wso2.ballerina.core.nativeimpl.AbstractNativeFunction;
import org.wso2.ballerina.core.nativeimpl.annotations.Argument;
import org.wso2.ballerina.core.nativeimpl.annotations.BallerinaFunction;

/**
 * Native function to remove given header to carbon message.
 * ballerina.lang.message:removeHeader
 */
@BallerinaFunction(
        packageName = "ballerina.lang.message",
        functionName = "removeHeader",
        args = {@Argument(name = "message", type = TypeEnum.MESSAGE),
                @Argument(name = "key", type = TypeEnum.STRING)},
        isPublic = true
)
@Component(
        name = "func.lang.message_removeHeader",
        immediate = true,
        service = AbstractNativeFunction.class
)
public class RemoveHeader extends AbstractNativeFunction {

    private static final Logger LOG = LoggerFactory.getLogger(RemoveHeader.class);

    @Override
    public BValue[] execute(Context context) {
        MessageValue msg = (MessageValue) getArgument(context, 0).getBValue();
        String headerName = getArgument(context, 1).getString();
        // Add new header.
        msg.removeHeader(headerName);
        if (LOG.isDebugEnabled()) {
            LOG.debug("Remove header:" + headerName);
        }
        return VOID_RETURN;
    }
}