/**
 * TOSCAna
 * To be Done!
 *
 * OpenAPI spec version: 1.0.0-SNAPSHOT
 * 
 *
 * NOTE: This class is auto generated by the swagger code generator program.
 * https://github.com/swagger-api/swagger-codegen.git
 * Do not edit the class manually.
 */
import { LifecyclePhase } from './lifecyclePhase';


export interface CsarResponse {
    /**
     * This value represents the name (Identifier) of this CSAR
     */
    name: string;
    /**
     * The lifecycle phases of this CSAR
     */
    phases: Array<LifecyclePhase>;
}
